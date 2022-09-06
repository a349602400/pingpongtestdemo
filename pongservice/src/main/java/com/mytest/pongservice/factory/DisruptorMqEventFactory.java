package com.mytest.pongservice.factory;

import com.lmax.disruptor.EventFactory;
import com.mytest.pongservice.event.MessageModel;

public class DisruptorMqEventFactory implements EventFactory<MessageModel> {

    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }

}
