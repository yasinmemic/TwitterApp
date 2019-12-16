import React from 'react'
import { Link } from 'react-router-dom'


class LeftComponent extends React.Component {
    constructor(props) {
        super(props)
        this.logOut = this.logOut.bind(this);
    }

    logOut() {
        localStorage.clear();
        var url = "/login"
        window.location.href = url;

    }

    render() {
        return (

            <div className="leftComponent">

                <div className="row">
                    <div className="hoverItem">
                        <a href="/home" className="fa fa-twitter img-fluid rounded-circle">{null}</a>
                    </div>
                </div>

                <div className="row"><a href="/home">
                    <button type="button" className="btn btn-outline-primary leftButtons"> <img src="https://img.icons8.com/wired/30/000000/home.png" alt="">
                    </img> Home</button></a>
                </div>

                <div className="row"><a href="/notification">
                    <button type="button" className="btn btn-outline-primary leftButtons">
                        <img src="https://img.icons8.com/dotty/30/000000/alarm.png" alt="" /> Notifications</button></a>
                </div>

                <div className="row">

                    <Link to={`/profile/${localStorage.getItem("userId")}`}>

                        <button type="button" className="btn btn-outline-primary leftButtons">
                            <img src="https://img.icons8.com/officel/30/000000/administrator-female.png" alt="" />
                            Profile</button>
                    </Link>
                </div>

                <div className="row"><a href="/messages">
                    <button type="button" className="btn btn-outline-primary leftButtons">
                        <img src="https://img.icons8.com/ios/30/000000/edit-message.png" alt="" /> Message</button></a>
                </div>

                <div className="row"><a href="/settings">
                    <button type="button" className="btn btn-outline-primary leftButtons">
                        <img src="https://img.icons8.com/nolan/30/000000/settings--v1.png" alt="" /> Settings </button></a>
                </div>
                <br /><br />

                <div className="row">
                    <button type="submit" className="btn btn-primary btn-block leftTweetButton" onClick={this.logOut}>Çıkış Yap</button>
                </div>



            </div>

        )
    }
}
export { LeftComponent }