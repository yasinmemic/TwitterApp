import React from 'react'
import axios from 'axios'
import { Redirect } from 'react-router-dom';

class Register extends React.Component {

    constructor(props) {
        super(props)

        this.register = this.register.bind(this)

    }


    register(e) {

        e.preventDefault()

        const firstName = e.target.elements.name.value
        const lastName = e.target.elements.lastName.value
        const userName = e.target.elements.username.value
        const email = e.target.elements.email.value
        const password = e.target.elements.password.value 
        var imgUrl = e.target.elements.imgUrl.value

       
       
      
        e.target.elements.name.value = ""
        e.target.elements.lastName.value = ""
        e.target.elements.username.value = ""
        e.target.elements.email.value = ""
        e.target.elements.password.value = ""
        e.target.elements.imgUrl.value = ""
        localStorage.removeItem('imgUrl')
      

        axios.post('http://localhost:8090/users',
            {
                firstName,
                lastName,
                userName,
                email,
                password,
                imgUrl

            }
        )
            .then((response) => {
                <Redirect to="/login" />
                console.log(response.data)
                localStorage.setItem('imgUrl',imgUrl)
                this.setState(() => {
                    return {
                        firstName,
                        lastName,
                        userName,
                        email,
                        password,
                        imgUrl
                    }
                })

            })
            .catch((error) => {

            })
    }


    Send() {
        var url = "/login"
        window.location.href = url;
    };

    render() {
        return (

            <div className="row">
                <div className="col-3"></div>
                <div className="col-6">

                    <div className="panel-heading">
                        <div className="panel-title text-center">
                            <h1 className="title">Register Twitter</h1>
                            <hr />
                        </div>
                    </div>
                    <div className="main-center">
                        <form className="form-horizontal" onSubmit={this.register}>

                            <div className="form-group" >

                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"></span>
                                        <input type="text" className="form-control" name="name" placeholder="Enter Your Firstname" style={{fontSize:'20px'}}/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">

                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"></span>
                                        <input type="text" className="form-control" name="lastName" placeholder="Enter Your Lastname" style={{fontSize:'20px'}} />
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">

                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"></span>
                                        <input type="text" className="form-control" name="username" placeholder="Enter Your Username" style={{fontSize:'20px'}}/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">

                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"></span>
                                        <input type="email" className="form-control" name="email" placeholder="Enter Your Email" style={{fontSize:'20px'}} />
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">

                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"></span>
                                        <input type="password" className="form-control" name="password" placeholder="Enter your Password" style={{fontSize:'20px'}}/>
                                    </div>
                                </div>

                               
                            </div>
                            <div className="form-group">

                            <div className="cols-sm-10">
                            <div className="input-group">
                                    <span className="input-group-addon"></span>
                                    <input type="text" className="form-control" name="imgUrl" placeholder="Enter profile picture URL  " style={{fontSize:'20px'}}/>
                                    </div>
                            </div>

                           
                        </div>
                            <div className="form-group ">
                                <button type="submit" style={{fontSize:'20px'}} className="btn btn-primary btn-lg btn-block" onClick={this.Send}>Register</button>
                            </div>
                            <div className="register">
                                <a href="/login" style={{fontSize:'20px'}}>Login</a>
                            </div>
                        </form>
                    </div>

                </div>
                <div className="col-3"></div>

            </div>
        )
    }
}

export default Register