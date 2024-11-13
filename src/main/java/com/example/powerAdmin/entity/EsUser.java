    package com.example.powerAdmin.entity;

    import lombok.Data;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.elasticsearch.annotations.Document;
    import org.springframework.data.elasticsearch.annotations.Field;
    import org.springframework.data.elasticsearch.annotations.FieldType;

    @Data
    @Document(indexName = "power_user")
    public class EsUser {
        @Id
        private String userId;

        @Field(type = FieldType.Text)
        private String userName;

        @Field(type = FieldType.Keyword)
        private String email;

        @Field(type = FieldType.Text)
        private String password;
    }
