import React, { Component } from 'react';
import '../App.css';
import axios from 'axios'
import { LeftComponent } from './LeftComponent.js'
import { RightComponent } from './RightComponent.js';


class Notification extends Component {

    constructor(props) {
        super(props)
        if (localStorage.getItem("token") == null) {
            window.location.href = "login";
        }
        this.handleRefresh = this.handleRefresh.bind(this)
        this.getRequests = this.getRequests.bind(this)
        this.acceptRequest = this.acceptRequest.bind(this)
        this.rejectRequest = this.rejectRequest.bind(this)
        this.state = {
            requests:
                [
                ],
        }
    }

    handleRefresh() {
        return new Promise((resolve) => {
            this.getUser()
        });
    }

    componentDidMount() {
        this.getRequests()
    }

    getRequests() {

        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/getRequests";

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
                const requests = response.data

                this.setState(() => {

                    return { requests }

                })
            })
    }

    acceptRequest(e) {

        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/acceptedRequest/" + e

        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }
        axios.post(api, {}, {
            headers: headers

        })
            .then((response) => {
                var x = document.getElementById("hiddenbutton");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
                this.handleRefresh()
                this.setState(() => {

                    return {}

                })
            })
    }

    rejectRequest(e) {
        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/rejectedRequest/" + e;

        console.log("rejectrequeste girdi")
        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }
        axios.post(api, {}, {
            headers: headers

        })
            .then((response) => {
                var x = document.getElementById("hiddenbutton");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }

                this.setState(() => {

                    return {}

                })
            })
    }

    render() {
        return (

            <div className="container">



                <div className="row">

                    <div className="col-3">  <LeftComponent /></div>
                    <div className="col-6">
                        <div className="row">
                        <div className="col-12">
                        <a href="/home" className="Home"><h2 className="underHomeLine">
                            <img src="https://img.icons8.com/officel/15/000000/back.png">

                            </img> Notifications
                        </h2></a></div></div>
                        <div className="row">
                        <div className="col-12">
                        <div className="main-body" style={{ textAlign: 'center', marginTop: '5%' }}>

                            {[...this.state.requests].map((request, index) => {

                                return (
                                    <div className="card" style={{ width: '20rem', marginTop: '2%' }} id="hiddenbutton"  >
                                        <div className="card-body">
                                            <h5 className="card-title"><img src={request.follower.imgUrl} alt="picture" className="picture" style={{ marginTop: '0%' }} /></h5>
                                            <h6 className="card-subtitle mb-2 text-muted">
                                                {request.follower.firstName} {request.follower.lastName}
                                            </h6>


                                            <div className="row">
                                         
                                                <div className="col-6"> <form onSubmit={() => this.acceptRequest(request.follower.id)} > <button type="submit" className="btn btn-primary">Accept</button>
                                                </form> </div>
                                                <div className="col-6">  <form onSubmit={() => this.rejectRequest(request.follower.id)} >
                                                    <button type="submit" className="btn btn-danger">Reject</button>
                                                </form></div>

                                            
                                            </div>
                                        </div>
                                    </div>

                                )
                            })}
                        </div>
                    </div></div></div>
                    <div className="col-3"> <RightComponent /> </div>
                </div>
            </div>
        );
    }
}

export default Notification
