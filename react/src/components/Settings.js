import React, { Component } from 'react';
import '../App.css';
import axios from 'axios'
import { LeftComponent } from './LeftComponent.js'
import { RightComponent } from './RightComponent.js';


class Settings extends Component {

    constructor(props) {
        super(props)
        if (localStorage.getItem("token") == null) {
            window.location.href = "login";
        }
        this.handleRefresh = this.handleRefresh.bind(this)
        this.removeAccount = this.removeAccount.bind(this)
        this.areYouSure = this.areYouSure.bind(this)
    }

    handleRefresh() {
        return new Promise((resolve) => {
            this.getUser()
        });
    }

   

    areYouSure(e) {
        e.preventDefault()
        console.log("am")

        swal({
            title: "Emin misiniz?",
            text: "Hesabınız kalıcı olarak silinecektir!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Hesabınız silindi!", {
                        icon: "success",
                    }
                    );
                    this.removeAccount(e)
                    window.location.href = "/login"
                } else {
                    swal("Kullanıma devam ettiğiniz için teşekkürler!");
                }
            });
    }


    removeAccount(e) {
        e.preventDefault()
        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const api = 'http://localhost:8090/users/' + id
        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)

        console.log(constLastToken)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.post(api, {},
            {
                headers: headers

            })
            .then((response) => {


                this.setState(() => {
                    return {

                    }
                })
            })
            .catch((error) => {
                console.log(error)

            })
    }






    render() {
        return (

            <div className="container">
                <div className="row">
                    <div className="col-3">  <LeftComponent /></div>
                    <div className="col-6">
                    <a href="/home" className="Home"><h2 className="underHomeLine">
                    <img src="https://img.icons8.com/officel/15/000000/back.png">

                    </img> Settings
                </h2></a>
                       

                        <div className="main-body" style={{ textAlign: 'center', marginTop: '5%' }}>

                            <div className="col-12">


                                <div className="row">
                                    <div className="col-6"> <strong> Hesabı kalıcı olarak sil  </strong></div>
                                    <div className="col-6">
                                        <form onSubmit={this.areYouSure}>
                                           
                                                <button type="submit" className="btn btn-outline-danger">
                                                    Hesabı Sil
                                              </button>
                                          
                                        </form></div>

                                </div>



                            </div>


                        </div>
                    </div>
                    <div className="col-3"> <RightComponent /> </div>
                </div>
            </div>
        );
    }
}

export default Settings
