# Endpoints

## V1
    - reply/{message} 
        Return message in JSON object

## V2
    - v2/reply/{rule}-{string}
        if rule = 1, then string will be reversed. if rule = 2, then string will be encode with md5 hash.
        Rule's length is not limited(Java String limits are applied).

# Deployment

## URL
https://string-reply.herokuapp.com/v2/reply

## Method

Deployed via GitHub actions to heroku

# Examples

## Ex1
    - https://string-reply.herokuapp.com/v2/reply/121-ex1 

## Ex2
    - https://string-reply.herokuapp.com/v2/reply/11-ex2

# Test

Use  **./gradlew test** to run test cases 
