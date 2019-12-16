import React, { Component } from 'react';
import '../App.css';
import axios from 'axios'
import { LeftComponent } from './LeftComponent.js'
import { RightComponent } from './RightComponent.js';
class FollowingList extends Component {

    constructor(props) {
        
        super(props)
        if (localStorage.getItem("token") == null) {
            window.location.href = "login";
        }
       
        this.getRequests = this.getRequests.bind(this)
        this.unFollow = this.unFollow.bind(this)
        this.state = {
            followings:
                [
                ],
                userId2:0
        }
    }


    componentDidMount(){
        this.getRequests()
        const { userId } = this.props.match.params
        this.state.userId2=userId;
        this.getRequests(this.state.userId2)
      }

    


    unFollow(id2) {
        if(id2 === undefined){
            id2 = localStorage.getItem("userId");
          }

        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/unfollow/" +id2;
        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.post(api, {},{
            headers: headers

        })
            .then((response) => {
                this.getRequests();
                this.setState(() => {

                    return {  }

                })
            })
            .catch((error) => {
                console.log()
            })
    }


    getRequests(id) {
        
          if(id === undefined){
            id = localStorage.getItem("userId");
            this.state.userId2 = id
          }
        const token = localStorage.getItem("token");
      //  const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/allFollowings";

        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.get(api, {
            headers: headers

        })
            .then((response) => {
                console.log(response)
                const followings = response.data

                this.setState(() => {

                    return { followings }

                })
            })
    }

    render() {
        return (

            <div className="container">
                <div className="row">
                    <div className="col-3">  <LeftComponent /></div>
                    <div className="col-6" style={{ textAlign: 'center', marginTop: '2%' }}>


                        <strong style={{ fontFamily: 'Helvetica Neue', fontSize: '35px' }}>Takip Edilenler</strong><span className="badge"></span>

                        <div className="main-body" style={{ textAlign: 'center', marginTop: '5%' }}>

                            {[...this.state.followings].map((following, index) => {
                                     
                                return (
                                    <div className="card" style={{ width: '20rem', marginTop: '2%' }} id="hiddenbutton"  >
                                        <div className="card-body">
                                            <h5 className="card-title"><img src={following.followed.imgUrl} alt="picture" className="picture" style={{ marginTop: '0%' }} /></h5>
                                            <h6 className="card-subtitle mb-2 text-muted">&nbsp; &nbsp; {following.followed.firstName} {following.followed.lastName}
                                            </h6>

                                            <div className="row">
                                            <div className="col-4"></div>
                                                <div className="col-4">                                       
                                                 <button type="submit" className="btn btn-primary"  onClick={ () => this.unFollow(following.followed.id)}>Unfollow</button>            
                                                </div>
                                                <div className="col-4"></div>
                                            </div>
                                        </div>
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

export default FollowingList
