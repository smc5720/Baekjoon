#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11728

int N, M;
int arr[2000000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N + M; i++)
	{
		cin >> arr[i];
	}

	sort(arr, arr + N + M);

	for (int i = 0; i < N + M; i++)
	{
		cout << arr[i] << " ";
	}

	cout << "\n";

	return 0;
}