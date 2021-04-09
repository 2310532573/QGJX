package com.luolin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DownFile implements Serializable {
    private String name;
    private byte[] file;
}