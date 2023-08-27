import java.util.*;

public class programmers_스킬트리 {
    static List<Character> saveSkill;

    public int solution(String skill, String[] skill_trees) {
        saveSkill = new ArrayList<>();

        for(int i = 0; i<skill.length(); i++){
            saveSkill.add(skill.charAt(i));
        }

        int answer = 0;

        for(int i = 0; i<skill_trees.length; i++){
            if(checkAvailable(skill_trees[i])) answer++;
        }

        return answer;
    }

    static boolean checkAvailable(String skillOrder){
        int index = 0;

        for(int i = 0; i<skillOrder.length(); i++){
            if(!saveSkill.contains(skillOrder.charAt(i))) continue;

            if(saveSkill.get(index) != skillOrder.charAt(i)) return false;
            else index++;
        }

        return true;
    }
}