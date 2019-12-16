import React, { Component } from 'react';
import  TweetBody from './tweet.js'
import { ProfileInfoComponent } from './ProfileInfoComponent.js'
import '../App.css';
import axios from 'axios'
import { LeftComponent } from './LeftComponent'
import { RightComponent } from './RightComponent';


class LikedTweets extends Component {
  constructor(props) {
    super(props)
    
    if(localStorage.getItem("token") == null){
      window.location.href = "/login";
    }
   
    this.state = {
      tweets:
        [
        ],
      usersTweet:
        [
        ],
        userId2:0
    
    }
    this.handleRefresh = this.handleRefresh.bind(this)
    this.getUser = this.getUser.bind(this)
    this.onRemovePressed = this.onRemovePressed.bind(this)
    this.likeTweet = this.likeTweet.bind(this)
    this.reTweet = this.reTweet.bind(this)
  
  }

  handleRefresh() {
    return new Promise((resolve) => {
      this.getUser()
    });
  }

  componentWillMount() {
    const { userId } = this.props.match.params
    this.state.userId2=userId;
    this.getUser(this.state.userId2)
  }



  onRemovePressed(e) {
    const tweetId = e.props.id
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("userId")
    const api = "http://localhost:8090/users/" + id + "/deleteTweet/" + tweetId;

    const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': constLastToken
    }

    axios.post(api, {}, {
      headers: headers

    })
      .then((response) => {
        this.getUser();
        this.setState(() => {

          return {}

        })
      })
  }
   
  likeTweet(e) {
    console.log(e)
    const tweetId = e.props.children[0].props.id

    const token = localStorage.getItem("token");
    const id = localStorage.getItem("userId")
    const api = "http://localhost:8090/users/" + id + "/liked/" + e.props.children[2].props.userId + "/tweets/" + tweetId;

    const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': constLastToken
    }
    axios.post(api, {}, {
      headers: headers

    })
      .then((response) => {
        this.getUser();
        this.setState(() => {

          return {

          }

        })
      })
  }

  reTweet(e) {
    console.log(e)
    const tweetId = e.props.children[0].props.id
    console.log(e)
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("userId")
    const api = "http://localhost:8090/users/" + id + "/rt/" + e.props.children[1].props.userId + "/tweets/" + tweetId;

    const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': constLastToken
    }
    axios.post(api, {}, {
      headers: headers

    })
      .then((response) => {
       this.getUser();
        this.setState(() => {

          return {

          }

        })
      })
  }

  getUser(id) {
    if(id === undefined){
      id = localStorage.getItem("userId");
    }
    const token = localStorage.getItem("token");
    //const id = localStorage.getItem("userId")
    const api = "http://localhost:8090/users/"+id+"/liked";
    console.log(api)
    
    const constLastToken = token.substring(token.indexOf("Bearer "),token.length)
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': constLastToken
    }

    axios.get(api, {
      headers: headers

    })
      .then((response) => {
       var name = response.data[0].user.firstName; 
       name += " "+response.data[0].user.lastName;
       console.log(name)
        
        const tweets = response.data
        const usersTweet = response.data
        this.setState(() => {

          return { tweets, usersTweet }

        })
      })
  }
  render() {
    return (

      <div className="container">
        <div className="row">
          <div className="col-3">  <LeftComponent /></div>
          <div className="col-6">


           <ProfileInfoComponent getUser={this.props.match.params} />
            <div className="main-body">
           
              {[...this.state.tweets].map((tweet, index) => {
                return (
                  <div>
                    <TweetBody
                      key={index}
                      id={tweet.id}
                      content={tweet.content}
                      rtCount={tweet.rtCount}
                      likedCount={tweet.likedCount}
                      createdAt={tweet.createdAt}
                      userName={this.state.usersTweet[index].user.userName}
                      firstName={this.state.usersTweet[index].user.firstName}
                      lastName={this.state.usersTweet[index].user.lastName}
                      picture={this.state.usersTweet[index].user.imgUrl}     
                      userId={this.state.usersTweet[index].user.id}
                      onRemovePressed={this.onRemovePressed}
                      likeTweet={this.likeTweet}
                      rtTweet={this.reTweet}
                   />
                  </div>
                )
              })}
            </div>
          </div>
          <div className="col-3"> <RightComponent /> </div>
        </div>
      </div>
    );
  }
}

export default LikedTweets
