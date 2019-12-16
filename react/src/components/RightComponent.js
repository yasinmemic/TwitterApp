import React from 'react'
import axios from 'axios'
import swal from 'sweetalert';


class RightComponent extends React.Component {
    constructor(props) {
        super(props)
        this.Follow = this.Follow.bind(this)

    }

    Follow(e) {
        e.preventDefault()
        const token = localStorage.getItem("token");
        const id = localStorage.getItem("userId")
        const username = e.target.elements.username.value
        const api = 'http://localhost:8090/users/' + id + '/requestFollow/' + username;
        const constLastToken = token.substring(token.indexOf("Bearer "), token.length)
        e.target.elements.username.value = ""
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
                if(response.data == true){
                swal("İstek gönderildi...", "", "success");
                }
               
                else{
                      swal("Hatalı takip isteği...", "", "error");
                }
                  
                
                this.setState(() => {
                    return {

                    }
                })
            })
            .catch((error) => {
                console.log(error)
                swal("Hatalı takip isteği...", "", "error");
            })
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-12">
                        <div id="wrap">
                            <form id="nav-search" className="form-search" onSubmit={this.Follow}>
                                <input className="search-input" type="text" placeholder="@İstek gönder" name="username" />
                                <button className="icon" type="submit" />
                            </form>
                        </div>
                    </div>
                </div>

                <div className="row" style={{ marginTop: '7%' }}>


                    <div className="col-12">
                        <div className="right" style={{ marginLeft: '0px', height: '40px' }}><strong>Istanbul Trends</strong></div>
                    </div>
                    <div className="col-12">
                        <div className="right">#</div>
                    </div>
                    <div className="col-12">
                        <div className="right">#</div>
                    </div>

                    <div className="col-12">
                        <div className="right">#</div>
                    </div>


                </div>


                <div className="row" style={{ marginTop: '10%' }}>
                </div>
            </div>
        )
    }
}
export { RightComponent }

