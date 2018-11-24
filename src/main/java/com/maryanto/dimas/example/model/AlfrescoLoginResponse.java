package com.maryanto.dimas.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlfrescoLoginResponse {

    @JsonProperty("entry")
    private AlfrescoEntryResponseLogin entry;

}
