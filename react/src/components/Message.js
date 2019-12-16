import React, { Component } from 'react';
import '../App.css';
import axios from 'axios'
import { LeftComponent } from './LeftComponent.js'
import { RightComponent } from './RightComponent.js';


class Message extends Component {

    constructor(props) {
        super(props)
        if (localStorage.getItem("token") == null) {
            window.location.href = "login";
        }
        this.handleRefresh = this.handleRefresh.bind(this)   
    }

    handleRefresh() {
        return new Promise((resolve) => {
            this.getUser()
        });
    }

    componentWillMount() {
        
    }


    render() {
        return (

            <div className="container">
                <div className="row">
                    <div className="col-3">  <LeftComponent /></div>
                    <div className="col-6">
                  
                    <a href="/home" className="Home"><h2 className="underHomeLine">
                    <img src="https://img.icons8.com/officel/15/000000/back.png">

                    </img> Message
                </h2></a>
                                        
                        <div className="main-body" style={{ textAlign: 'center', marginTop: '5%' }}>
          
                        </div>
                    </div>
                    <div className="col-3"> <RightComponent /> </div>
                </div>
            </div>
        );
    }
}

export default Message
