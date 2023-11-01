package com.starterkitsusmb.backspringboot.auth;

import java.util.List;

public record Token(String jwt, List<String> roles) {
}
