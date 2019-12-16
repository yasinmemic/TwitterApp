import React from 'react';
import { Link  } from 'react-router-dom'

const TweetBox = (props) => {
  return (
    <div className="tweet-body">
      {props.children}
    </div>
  )
}

const Id = (props) => {
  return
  { props.id }
}


const UserId = (props) => {
  return
  { props.userId }
}


const Tweet = (props) => {
  return (
    <div className="tweet">
      {props.content}
    </div>
  )
}


const LikedTweet = (props) => {

  return (
    <i className="fa fa-thumbs-up likeButton" style={{ fontSize: '15px' }}> {props.likedCount}</i>
  )

}
const RtTweet = (props) => {
  return (
    <i className="fa fa-thumbs-up reTweetButton" style={{ fontSize: '15px' }}> {props.rtCount}</i>
  )

}

const Name = (props) => {
  return (
    <div className="name">

      <strong className="Home">{props.firstName} {props.lastName} </strong>

    </div>
  )
}

const UserName = (props) => {
  return (
    <div className="userName">

      <a href="javascript:history.go(0)" className="Home">@{props.userName} </a>

    </div>
  )
}

const SharingDate = (props) => {
  return (

    <div className="date">{props.createdAt.substring(11, 16)}</div>
  )
}

const Picture = (props) => {
  return (
    <img src={props.picture} alt="Logo" className="picture">
    </img>
  )
}
const Delete = (props) => {
  return (

    <i className="fa fa-trash" style={{ fontSize: '15px' }}>Delete</i>

  )

}
const TweetBody = (props) => {

  return (
    <TweetBox>
      <div className="inner-body">
        <div className="body">
          <div className="inner-body">
            <div className="container">
              <div className="row">
                <div className="col-2">
                  <Picture picture={props.picture} />
                </div>

                <div className="col-8">
                  <div className="row">
                    <div className="col-4">
                      <Link to={`/profile/${props.userId}`} style={{ textDecoration: 'none' }}>
                        <Name firstName={props.firstName} lastName={props.lastName} />
                      </Link>
                    </div>
                    <div className="col-4"><UserName userName={props.userName} /> </div>
                    <div className="col-4"><SharingDate createdAt={props.createdAt} /></div>
                  </div>
                  <div className="row">
                    <div className="col-12"><Tweet content={props.content} />
                    </div>
                  </div>
                  <div className="col-2">
                  </div>
                  <div className="row">
                    {
                      localStorage.getItem("userId") != props.userId ?
                        <div className="col-4">
                          <div className="rt">
                            <i className="fa fa-retweet" id="reTweetButton" style={{ fontSize: '15px' }} onClick={() =>
                              <RtTweet rtTweet={props.rtTweet(<div><Id id={props.id} /><UserId userId={props.userId} /> </div>)}>
                              </RtTweet>
                            }>{props.rtCount}</i>
                          </div>
                        </div>
                        : console.log()
                    }
                    <div className="col-4"><div className="like">
                      <i className="fa fa-thumbs-up" id="reTweetButton" style={{ fontSize: '15px' }} onClick={() =>
                        <LikedTweet likeTweet={props.likeTweet(<div><Id id={props.id} /> <UserId userId={props.userId} /> </div>)}>
                        </LikedTweet>
                      }>   {props.likedCount}</i>
                    </div>
                    </div>
                    {
                      localStorage.getItem("userId") == props.userId ?
                        <div className="col-4">
                          <div className="rt">
                            <i className="fa fa-trash" id="reTweetButton" style={{ fontSize: '15px' }} onClick={() =>
                              <Delete onRemovePressed={props.onRemovePressed(<Id id={props.id} />)}>
                              </Delete>
                            }></i>
                          </div>
                        </div>
                        : console.log()
                    }
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </TweetBox>
  )
}

export default TweetBody 