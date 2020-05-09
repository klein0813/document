方式一
----
*  git add -A
*  git commit -m 'feat(nikedream): add create my dream page'
*  git branch
*  git branch -D feat-create-my-dream-bk
*  git  branch -m feat-create-my-dream feat-create-my-dream-bk
*  git rebase origin develop
*  git checkout -b feat-create-my-dream
*  git branch
*  git merge --squash feat-create-my-dream-bk
*  git commit -m 'feat(nikedream): add create my dream page'
*  git push -f origin feat-create-my-dream

方式二
----
*  git branch
*  git fetch
*  git rebase origin/develop 
*  git add .
*  git rebase --continue
*  git push origin feat-create-my-dream -f
*  git rebase -i HEAD~8
*  git push origin feat-create-my-dream -f
