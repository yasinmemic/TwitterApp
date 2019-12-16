import React from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'
class ProfileInfoComponent extends React.Component {

    constructor(props) {
        super(props)

        this.getRequests = this.getRequests.bind(this)
        this.countOfFollower = this.countOfFollower.bind(this)
        this.countOfFollowed = this.countOfFollowed.bind(this)

        this.state = {
            userInfo:
                [],
            followerCount: 0,
            followedCount: 0,
            joningDate: null,
            joningDate2: null,
            userId: 0
        }
    }

    componentDidMount() {
        const { userId } = this.props.getUser
        console.log(userId)
        this.getRequests()
        this.countOfFollower()
        this.countOfFollowed()
    }

    getRequests(id) {
        if (id === undefined) {
            const { userId } = this.props.getUser
            id = userId

        }
        else {
            id = localStorage.getItem("userId");
        }

        const token = localStorage.getItem("token");
        //  const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/profile/info"
        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.get(api, {
            headers: headers

        })
            .then((response) => {
                const userInfo = response.data
                console.log(response)
                this.setState(() => {
                    this.state.joningDate = userInfo.createdAt.substring(2, 4)
                    this.state.joningDate2 = userInfo.createdAt.substring(5, 8)
                    return { userInfo }

                })
            })
    }

    countOfFollower(id) {
        if (id === undefined) {
            const { userId } = this.props.getUser
            id = userId

        }
        else {
            id = localStorage.getItem("userId");
        }
        const token = localStorage.getItem("token");
        // const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/followerCount"

        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.get(api, {
            headers: headers

        })
            .then((response) => {

                const countOfFollower = response.data

                this.setState(() => {
                    this.state.followerCount = countOfFollower
                    return { countOfFollower }

                })
            })
    }

    countOfFollowed(id) {
        if (id === undefined) {
            const { userId } = this.props.getUser
            this.state.userId = userId
            id = userId

        }
        else {
            id = localStorage.getItem("userId");
        }

        const token = localStorage.getItem("token");
        //   const id = localStorage.getItem("userId")
        const api = "http://localhost:8090/users/" + id + "/followedCount"

        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.get(api, {
            headers: headers

        })
            .then((response) => {
                const countOfFollowed = response.data

                this.setState(() => {
                    this.state.followedCount = countOfFollowed
                    return { countOfFollowed }

                })
            })
    }


    render() {
        return (
            <div className="addTweet" style={{ backgroundColor: ' #f0f0f0' }}>

                <a href="/home" className="Home"><h2 className="underHomeLine">
                    <img src="https://img.icons8.com/officel/15/000000/back.png">

                    </img> {this.state.userInfo.firstName}  {this.state.userInfo.lastName}
                </h2></a>

                <div className="row">
                    <div className="col-6">
                        <div className="row">
                            <div className="col-12">
                                <img src={this.state.userInfo.imgUrl} className="rounded-circle profilePagePicture" />

                            </div>
                        </div>

                        <div className="row" style={{marginTop:'40%'}}>
                            <div className="col-12">
                            {
                                localStorage.getItem("userId") == this.state.userId ?
                                  <div className="col-4">
                                  <button type="button" className="btn btn-info">Profili Düzenle</button>

                                  </div>
                                  : console.log()
                              }
                            </div>
                        </div>

                    </div>

                    <div className="col-6">
                        <div className="row">
                            <div className="col-6">
                                <strong> {this.state.userInfo.firstName} {this.state.userInfo.lastName}</strong>
                            </div>
                            <div className="col-6"> <strong> @{this.state.userInfo.userName}</strong>
                            </div>
                        </div>



                        <div className="row" style={{ marginTop: '5%' }}>

                            <div className="col-4">

                                <div className="panel panel-success">
                                    <div className="panel-heading"> Followers Count</div>
                                    <Link to={`/./followers/${this.state.userId}`} style={{ textDecoration: 'none' }} >
                                        <div className="panel-body count">{this.state.followedCount}</div>
                                    </Link>
                                </div>
                            </div>
                            <div className="col-4">
                                <div className="panel panel-success">
                                    <div className="panel-heading"> Following Count</div>
                                    <Link to={`/./following/${this.state.userId}`} style={{ textDecoration: 'none' }} >
                                        <div className="panel-body count">{this.state.followerCount}</div>
                                    </Link>
                                </div>
                            </div>

                            <div className="col-4">
                                <div className="panel panel-warning">
                                    <div className="panel-heading">Date of Joining</div>
                                    <div className="panel-body">{this.state.joningDate2}{this.state.joningDate} </div>
                                </div>
                            </div>



                        </div>
                        <div className="row">
                            <div className="col-4"></div>
                            <div className="col-4">

                                <div className="panel panel-info">
                                    <div className="panel-heading"> Liked Tweets</div>
                                    <Link to={`/./likedTweets/${this.state.userId}`} style={{ textDecoration: 'none' }} >
                                 
                                        <div className="panel-body count">Show Tweets</div>
                                    </Link>
                                </div>
                            </div>
                            <div className="col-4"></div>
                        </div>
                    </div>
                </div>
            </div>



        )
    }

}
export { ProfileInfoComponent }





