import React, { Component } from 'react';
import '../App.css';
import axios from 'axios'
import { LeftComponent } from './LeftComponent.js'
import { RightComponent } from './RightComponent.js';

class FollowerList extends Component {

    constructor(props) {
    
        super(props)
        if (localStorage.getItem("token") == null) {
            window.location.href = "login";
        }
       
        this.getRequests = this.getRequests.bind(this)
       
        this.state = {
            followers:
                [
                ],
                userId2:this.props.match.params.userId
        }
    }
    
   

    componentDidMount(){
        // const  {userId}  = this.props.match.params
        // this.state.userId2=userId;
        this.getRequests(this.state.userId2)
      }

    getRequests(id) {
       
        if(id === undefined){
            id = localStorage.getItem("userId");
            this.state.userId2 = id
          }
        const token = localStorage.getItem("token");
       // const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + this.state.userId2 + "/getAllFollowers";

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
                const followers = response.data
                console.log("XX")
                this.setState(() => {
                    return { followers }
                })
            })
    }

    render() {
        return (

            <div className="container">
                <div className="row">
                    <div className="col-3">  <LeftComponent /></div>
                    <div className="col-6" style={{ textAlign: 'center', marginTop: '2%' }}>
                        <strong style={{ fontFamily: 'Helvetica Neue', fontSize: '35px' }}>Takipçiler</strong><span class="badge"></span>

                        <div className="main-body" style={{ textAlign: 'center', marginTop: '5%' }}>

                            {[...this.state.followers].map((follower, index) => {
                                     
                                return (
                                    <div className="card" style={{ width: '20rem', marginTop: '2%' }} id="hiddenbutton"  >
                                        <div className="card-body">
                                            <h5 className="card-title"><img src={follower.follower.imgUrl} alt="picture" className="picture" style={{ marginTop: '0%' }} /></h5>
                                            <h6 className="card-subtitle mb-2 text-muted">
                                                {follower.follower.firstName} {follower.follower.lastName}
                                            </h6>

                                          
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

export default FollowerList
