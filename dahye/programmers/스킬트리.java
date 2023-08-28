package dahye.programmers;

class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i = 0; i < skill_trees.length; i++) {
            String tmp = "";
            for(int j = 0; j < skill_trees[i].length(); j++) {
                if(skill.contains(String.valueOf(skill_trees[i].charAt(j)))) tmp += skill_trees[i].charAt(j);

            }

            if(tmp.length() == 0) answer++;
            else {
                if(tmp.charAt(0) == skill.charAt(0) && skill.contains(tmp)) answer++;
            }
        }

        return answer;
    }
}