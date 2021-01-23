package com.key_manager.dto;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Note implements Serializable {
    private List<OneWrite> listWrites;
    private final File file;
    private final ObjectMapper mapper;


    public Note() throws IOException {
        listWrites = new ArrayList<OneWrite>();
        mapper = new ObjectMapper();
        file = new File( "pass_data.json");
        if(!file.exists()){
            FileWriter fw = new FileWriter(file);
            fw.write("[]");
            fw.close();
        }
    }

    public void add(String password, String login, String from, String description) {
        listWrites.add(new OneWrite(password, login, from, description));
    }

    public OneWrite searchForLogin(String login) {
        return listWrites.stream()
                .filter(oneWrite -> login.equals(oneWrite.getLogin()))
                .findAny()
                .orElse(null);
    }

    public OneWrite searchForFrom(String from) {
        return listWrites.stream()
                .filter(oneWrite -> from.equals(oneWrite.getFrom()))
                .findAny()
                .orElse(null);
    }

    public List<OneWrite> getListWrites() {
        return listWrites;
    }

    public void deletePassword(OneWrite oneWrite) {
        for (int i = 0; i < listWrites.size(); i++) {
            if (oneWrite.equals(listWrites.get(i))) {
                listWrites.remove(i);
                return;
            }
        }
    }

    public void serialize() throws IOException {
        mapper.writeValue(file, listWrites);
    }

    public void deserialize() throws IOException {
        listWrites = mapper.readValue(file,
                new TypeReference<List<OneWrite>>() {
                });

    }
}
