package com.example.demo.entity;

import lombok.Data;

@Data
public class TranscriptionDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 讲话的人
     */
    private String speaker;

    /**
     * 笔录内容
     */
    private String transcript;

    public TranscriptionDTO(Long id, String startTime, String endTime, String speaker, String transcript) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speaker = speaker;
        this.transcript = transcript;
    }

    public TranscriptionDTO() {

    }
}
