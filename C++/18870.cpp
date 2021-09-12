#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/18870

int N;
int X[1000000];
vector <int> v;
map <int, int> m;

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> X[i];
		v.push_back(X[i]);
	}

	sort(v.begin(), v.end());
	v.resize(unique(v.begin(), v.end()) - v.begin());

	int idx, size;
	size = v.size();
	idx = 0;

	for (int i = 0; i < size; i++)
	{
		m.insert(make_pair(v[i], idx));
		idx += 1;
	}

	for (int i = 0; i < N; i++)
	{
		int n;
		n = X[i];
		cout << m[n] << " ";
	}

	cout << "\n";

	return 0;
}