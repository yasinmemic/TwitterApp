import React from 'react'
import axios from 'axios'

class AddTweet extends React.Component {
    constructor(props) {
        super(props)
        this.addTweetClick = this.addTweetClick.bind(this);
        this.getUser = this.getUser.bind(this);
       
        this.state = {
            imgUrl: null
        }
    }

    componentDidMount() {
        this.getUser()
    }



    getUser() {
        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
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
                this.setState(() => {
                    this.state.imgUrl = response.data.imgUrl
                    return {}

                })
            })
    }




    render() {
      
        return (
            <div className="addTweet">
                <a href="/home" className="Home"><h2 className="underHomeLine"> Home </h2></a>

                <div className="row">
                    <div className="col-3">

                        <img src={this.state.imgUrl} alt="Logo" className="picture" />
                    </div>

                    <div className="col-9">
                        <div className="row">
                            <div className="col-12">
                                <form onSubmit={this.addTweetClick}>
                                    <textarea className="typeTweet" placeholder="Neler oluyor?" name="content" ></textarea> <br />
                                    <button className="btn btn-outline-primary tweetButton">Tweet</button>
                                    
                                </form>
                            </div>
                        </div>
                        <div className="row" style={{ marginTop: '3%' }}>
                            <div className="col-4" >
                                <a href="/home"> <img src="https://img.icons8.com/wired/30/000000/small-smile.png" /></a>
                                &nbsp; &nbsp;
                            <a href="/home"><img src="https://img.icons8.com/nolan/30/000000/line-chart.png" /></a>
                            </div>

                            <div className="col-4">
                                <a href="/home"><img src="https://img.icons8.com/office/30/000000/gif.png" /></a>
                                <a href="/home"><img src="https://img.icons8.com/nolan/30/000000/photo-editor.png" /></a>
                            </div>
                            <div className="col-4"></div>
                        </div>
                    </div>
                </div>


            </div>
        )
    }
    addTweetClick(e) {
        e.preventDefault()
        const content = e.target.elements.content.value

        e.target.elements.content.value = ""

        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)

       
        const api = "http://localhost:8090/users/" + id + "/tweets";
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': constLastToken
        }

        axios.post(api, { content: content },
            {
                headers: headers
            })
            .then((response) => {
               window.location.href= "javascript:history.go(0)"
            
                console.log(response.data)
                this.setState((current) => {
                    return {
                        content
                    }
                })
            })
            .catch((error) => {
            })
    }
}
export { AddTweet }