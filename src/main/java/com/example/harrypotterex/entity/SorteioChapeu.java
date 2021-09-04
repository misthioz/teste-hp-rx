package com.example.harrypotterex.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SorteioChapeu {
    @SerializedName("sorting-hat-choice")
    private String id;
}
