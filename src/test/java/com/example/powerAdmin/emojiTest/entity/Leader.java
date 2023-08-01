package com.example.powerAdmin.emojiTest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leader {
    @JsonValue
    private String leaderName;
    private Integer age;
    private String nation;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Leader getLeaderByName(String leaderName) {
        if ("忽必烈".equals(leaderName)) {
            Leader leader = new Leader();
            leader.setLeaderName(leaderName);
            leader.setAge(79);
            leader.setNation("蒙古族");
            return leader;
        }
        return null;
    }
}