package com.micropos.email.publisher;

import com.micropos.email.dto.EmailMetaData;

public interface EventPublisher {

	void proceedEmailEvent(EmailMetaData emailMetaData);

}
