package org.hackathon.apis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hackathon.apis.model.DevDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequestBody {

    private DevDto devDto;

    private String action;
}
