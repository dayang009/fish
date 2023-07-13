echo search ing ...
find . -name "*lastUpdated" | xargs rm -rf
find . -name "_remote.repositories" | xargs rm -rf
echo The End.