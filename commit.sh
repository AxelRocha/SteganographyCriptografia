function getBranch() {
  git branch --show-current
}

git add . && git commit -m "$(getBranch)" && git push


$SHELL