// Rel native launcher for Windows.

#include "pch.h"
#include <windows.h>
#include <stdio.h>

int APIENTRY wWinMain(_In_ HINSTANCE hInstance,
	_In_opt_ HINSTANCE hPrevInstance,
	_In_ LPWSTR    lpCmdLine,
	_In_ int       nCmdShow)
{
	SECURITY_ATTRIBUTES sa;
	sa.nLength = sizeof(sa);
	sa.lpSecurityDescriptor = NULL;
	sa.bInheritHandle = TRUE;

	DWORD mode = FILE_SHARE_WRITE | FILE_SHARE_READ;
	HANDLE h = CreateFile(TEXT("out.log"), FILE_APPEND_DATA, mode, &sa, OPEN_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL);

	PROCESS_INFORMATION pi;
	STARTUPINFO si;
	BOOL ret = FALSE;
	DWORD flags = CREATE_NO_WINDOW;

	ZeroMemory(&pi, sizeof(PROCESS_INFORMATION));
	ZeroMemory(&si, sizeof(STARTUPINFO));
	si.cb = sizeof(STARTUPINFO);
	si.dwFlags |= STARTF_USESTDHANDLES;
	si.hStdInput = NULL;
	si.hStdError = h;
	si.hStdOutput = h;

	TCHAR cmd[] = TEXT("jre\\bin\\java -splash:lib\\Splash.png -cp \"lib\\*;lib\\nattable\\*;lib\\swt\\*;lib\\swt\\win_64\\*\" DBrowser ");
	if (CreateProcess(NULL, cmd, NULL, NULL, TRUE, flags, NULL, NULL, &si, &pi))
	{
		CloseHandle(pi.hProcess);
		CloseHandle(pi.hThread);
		return 0;
	}

	return -1;
/*



	STARTUPINFOA si;
	PROCESS_INFORMATION pi;

	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));

	char *cmd = (char *)"jre\\bin\\java -splash:lib\\Splash.png -cp \"lib\\*;lib\\nattable\\*;lib\\swt\\*;lib\\swt\\win_64\\*\" DBrowser ";

	// Start the child process. 
	if (!CreateProcessA(NULL,   // No module name (use command line)
		cmd,            // Command line
		NULL,           // Process handle not inheritable
		NULL,           // Thread handle not inheritable
		FALSE,          // Set handle inheritance to FALSE
		0,              // No creation flags
		NULL,           // Use parent's environment block
		NULL,           // Use parent's starting directory 
		&si,            // Pointer to STARTUPINFO structure
		&pi)           // Pointer to PROCESS_INFORMATION structure
		)
	{
		printf("CreateProcess failed (%d).\n", GetLastError());
		return 1;
	}

	// Wait until child process exits.
	WaitForSingleObject(pi.hProcess, INFINITE);

	// Close process and thread handles. 
	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);

	return 0;
	*/
}
