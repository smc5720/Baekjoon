#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1543

string text;
string word;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	getline(cin, text);
	getline(cin, word);

	int textSize, wordSize;

	textSize = text.size();
	wordSize = word.size();

	int sum;
	sum = 0;

	for (int i = 0; i <= textSize - wordSize; i++)
	{
		if (text[i] == word[0])
		{
			for (int j = 0; j < wordSize; j++)
			{
				if (text[i + j] != word[j])
				{
					break;
				}

				if (j == wordSize - 1)
				{
					sum += 1;
					i += j;
				}
			}
		}
	}

	printf("%d\n", sum);

	return 0;
}