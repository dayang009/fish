package com.fish.user.entity;

import com.fish.user.common.SimplePage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoReq extends SimplePage {

	private static final long serialVersionUID = 8784121548952112660L;

	private String name;

}
