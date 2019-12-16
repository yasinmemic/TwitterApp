import React, { Fragment } from 'react'
import { NavLink, Link } from 'react-router-dom'
export default () => {
    return (
        <Fragment>
            <NavLink to="/"></NavLink>
            <NavLink to="/home"></NavLink>
            <Link to="/profile/:userId"></Link>
            <Link to="/followers/:userId"></Link>
            <Link to="/following/:userId"></Link>
            <NavLink to="/register"></NavLink>
            <NavLink to="/login"></NavLink>
        </Fragment>
    )
}