package hyeongseok.programmers;

import java.util.ArrayList;
import java.util.List;

public class 스킬트리 {

	public int solution(String skill, String[] skill_trees) {
		int answer = 0;

		boolean[] alpha = new boolean[26];

		for (int i = 0 ; i < skill.length() ; i++) {
			alpha[skill.charAt(i) - 'A'] = true;
		}

		for (int i = 0 ; i < skill_trees.length ; i++) {
			String str = skill_trees[i];

			String result = "";

			for (int j = 0 ; j < str.length() ; j++) {
				if (alpha[str.charAt(j) - 'A']) {
					result += str.charAt(j);
				}
			}

			boolean check = true;

			for (int j = 0 ; j < result.length() ; j++) {
				if (result.charAt(j) != skill.charAt(j)) {
					check = false;
					break;
				}
			}

			if (check)
				answer++;
		}

		return answer;
	}


}
