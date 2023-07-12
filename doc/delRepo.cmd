set REPOSITORY_PATH=D:\Develop\LocalRepository
rem Searching ...
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*_remote.repositories*"') do (
    del /s /q %%i
)
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    del /s /q %%i
)
rem Delete Success !!!
pause 