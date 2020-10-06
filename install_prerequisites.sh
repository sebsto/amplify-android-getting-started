#!/bin/zsh

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
