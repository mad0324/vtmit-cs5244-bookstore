npm run build
rm -rf ../server/src/main/webapp/{css,img,index.html,index.jsp,js,favicon.io}
\cp -r dist/* ../server/src/main/webapp/
echo Deployed.