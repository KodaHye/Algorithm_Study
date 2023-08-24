package hyeongseok.programmers;

public class 스킬트리 {

	public int[] alpha;

	// "CBD"	["BACDE", "CBADF", "AECB", "BDA"]
	public int solution(String skill, String[] skill_trees) {
		int answer = -1;

		alpha = new int[skill.length()];

		for (int i = 0 ; i < skill.length() ; i++) {
			alpha[skill.charAt(i) - 'A'] = i+1;
		}

		return answer;
	}


}
