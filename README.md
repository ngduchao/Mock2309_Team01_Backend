#Mock2309_Team01_Frontend

git clone link.git // kéo kho/thư mục lưu trữ source code của Dựa án về trên máy

git checkout -b newBranchName // tạo nhánh mới từ nhánh hiện tại. main -> develop


// coding

git status		  		 // check changes: kiểm tra thay đổi
git add . 		  		 // git add folder/file.java
git commit -m "message"  //version on local
git push origin branch_name		  //git push -u origin master    create & push branch

// merging
git checkout develop
git pull origin branch_name  // lấy source code mới nhất từ một nhánh

git checkout yourBranchName
git merge develop		// copy source code mới nhất từ develop vào nhánh hiện tại
// fix conflict if exists
git status
git add .
git commit -m ""
git push

// create merge request in git UI


// other statement
 git checkout HEAD -- file.java		// rollback file
 git checkout commit_code			// rollback by commit
 git reset --hard					// rollback to previous commit
 
git config --global user.name "Tên người dùng"
git config --global user.email "Địa chỉ mail"
 
Tutorial: https://backlog.com/git-tutorial/vn/contents/


 git checkout HEAD -- file_name //rollback file: reset về file ban đầu
 git checkout commit_code //rollback commit: rollback về commit theo mã commit
 

git log //
