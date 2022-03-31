package org.essaadani.customermscqrseventsourcing.commonapi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BaseEvent <T>{
    @Getter private T id;
}
