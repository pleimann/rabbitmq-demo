package com.example.messaging.rabbitmq;

import java.io.Serializable;

public record UserMessage(long id, String userName, String messageText) implements Serializable { }