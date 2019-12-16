import React from 'react'
import axios from 'axios'
import swal from 'sweetalert';


class Login extends React.Component {
    
    constructor (props) {
        super(props)
        this.loginUser = this.loginUser.bind(this)
        this.auth = this.auth.bind(this)
    }

    auth(e) {
        this.loginUser(e) 

      }
      
      componentDidMount(){
        if (localStorage.getItem("token") != null) {
            window.location.href = "/home";
          }
      }

    loginUser(e) {
        e.preventDefault()
        const userName = e.target.elements.username.value
        const password = e.target.elements.password.value

        e.target.elements.username.value = ""
        e.target.elements.password.value = ""

        axios.post('http://localhost:8090/login',
            {          
                userName,
                password
            }
        )
            .then( (response) => {
                const token = response.data        
               
                localStorage.setItem('token',response.data)
                var id = token.substring(0,token.indexOf(" "));
                localStorage.setItem('userId',id)
                console.log(id)
               
                console.log(token)
                this.setState(() => {
                    return {        
                        userName,
                        password
                    }
                   
                   })
                   this.Send() 
            })
            .catch(() => {
                swal("Kullanıcı adı veya parola hatalı!", "", "error");
            })
    }

    Send() {
        var url = "/home"
        window.location.href = url;
    };



    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-3"></div>
                    <div className="col-6">

                            <div className="card">
                                <article className="card-body">
                                    <h4 className="card-title text-center mb-4 mt-1">Sign in</h4>
                                    <hr />
                                    <p className="text-success text-center">Please Login</p>
                                    <form onSubmit={this.auth}>
                                        <div className="form-group">
                                            <div className="input-group">
                                                <div className="input-group-prepend">
                                                    <span className="input-group-text"> 
                                                    <i className="fa fa-user" style={{fontSize:'10px'}}/>

                                                    </span>
                                                </div>
                                                <input name="username" className="form-control" placeholder="username" type="text" style={{fontSize:'20px'}}/>
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <div className="input-group">
                                                <div className="input-group-prepend">
                                                    <span className="input-group-text"> <i className="fa fa-lock"  style={{fontSize:'10px'}}></i> </span>
                                                </div>
                                                <input className="form-control" placeholder="password" type="password" name="password" style={{fontSize:'20px'}}/>
                                            </div>
                                        </div>
                                       <div className="form-group">
                                            <button type="submit" className="btn btn-primary btn-block" style={{fontSize:'20px'}}> Login  </button>
                                        </div>

                                        <div className="register">
                                            <a href="/register" style={{fontSize:'20px'}}>Sign up</a>
                                        </div>
                                    </form>

                                </article>
                            </div>
                    </div>
                    <div className="col-3"></div>
                </div>
            </div>
        )
    }
}
export default Login