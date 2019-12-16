import React from 'react'
import { BrowserRouter, Route, Switch } from 'react-router-dom'

import Login from './Login'
import Home from './Home'
import Profile from './Profile'
import Register from './Register'
import Notifications from './Notifications'
import FollowingList from './FollowingList'
import FollowerList from './FollowerList'
import LikedTweets from './likedTweets'
import Message from './Message'
import Settings from './Settings'

export default () => {
  
    return (
        <BrowserRouter>
        <Switch>
                <Route component={Profile} path="/profile/:userId"  />
                <Route component={FollowingList} path="/./following/:userId" />
                <Route component={Message} path="/messages" />
                <Route component={FollowerList} path="/./followers/:userId" />
                <Route component={LikedTweets} path="/./likedTweets/:userId" />
                <Route component={Login} path="/" exact={true} />
                <Route component={Login} path="/login" />
                <Route component={Register} path="/register" />
                <Route component={Home} path="/home" />
                <Route component={Notifications} path="/notification" />
                <Route component={Settings} path="/settings"/>
            </Switch>
        </BrowserRouter>
    )
}