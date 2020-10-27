# Introduction

## Overview

In this tutorial, you will create a simple Android application using AWS Amplify, a set of tools and serverless services in the cloud. In the first module, you’ll build a simple Android application. Through the remaining modules, you will initialize a local app using the Amplify Command Line Interface (Amplify CLI), add user authentication, add a GraphQL API and a database to store your data, and update your app to store images.

## What you Will Learn

This tutorial will walk you through the steps to create a simple Android application discussed above. You will learn to:

- Manage serverless cloud backend from the command line

- Add auth to your app to enable sign-in and sign-out

- Add a GraphQL API, database, and storage solution

- Share your backend between multiple projects

## Modules

This tutorial is divided into five short modules. You must complete each module in order, before moving on to the next one.

- [Create an Android App](02_create_android_app.md) (10 minutes): Create an Android app and test it in the Android simulator.

- [Initialize a Local App](03_initialize_amplify.md) (10 minutes): Initialize a local app using AWS Amplify.

- [Add Authentication](04_add_authentication.md) (10 minutes): Add auth to your application.

- [Add a GraphQL API and Database](05_add_api_database.md) (20 minutes): Create a GraphQL API.

- [Add the Ability to Store Images](06_add_storage.md) (10 minutes): Add storage to your app.

You will be building this Android application using the [Terminal](https://support.apple.com/en-gb/guide/terminal/welcome/mac) and Google's [Android Studio](https://developer.android.com/studio) IDE.

## Side Bar

| Info | Level |
| --- | --- |
| ✅ AWS Level    | Beginner |
| ✅ Android Level    | Beginner |
| ✅ Swift Level  | Beginner |
| ⏱ Time to complete | 1h |
| 💰 Cost to complete | [Free tier](https://aws.amazon.com/free) eligible |

## Tutorial pre-requisites

This tutorial has been developed and tested on macOS but should work also on Windows and Linux.

To follow this tutorial, you need the following tools and resources:

- [Android Studio 4.x](https://developer.android.com/studio) or more recent.
- an [AWS Account](https://portal.aws.amazon.com/billing/signup#/start) with at least [these permissions](/amplify-policy.json) (an `Administrator` role or `root` account will also work, but we recommend a [least-privileges](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html#grant-least-privilege) approach).
- [NodeJS 10.x](https://nodejs.org/en/download/) or more recent.
- AWS Command Line Interface [AWS CLI 2.0.x](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html) or more recent.

On Linux and Mac, you can install these tools following these instructions:

```zsh
# file available at https://github.com/sebsto/amplify-android-getting-started/blob/main/install_prerequisites.sh 

# install brew itself
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

# install python3 and pip3
brew install python3

# install the AWS CLI
brew install awscli

# install Node.js & npm
brew install node

#
# Verification (Actual version might be more recent)
#

brew --version
# Homebrew 2.5.1
# Homebrew/homebrew-core (git revision 405765; last commit 2020-09-18)

python3 --version
# Python 3.8.5

aws --version
# aws-cli/2.0.54 Python/3.8.5 Darwin/19.6.0 source/x86_64

node --version
# v14.11.0
```

[Next](/02_create_android_app.md) : Create an Android App.