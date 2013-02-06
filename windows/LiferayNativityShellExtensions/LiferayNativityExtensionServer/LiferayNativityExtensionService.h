/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

#ifndef LIFERAYNATIVITYEXTENSIONSERVICE_H
#define LIFERAYNATIVITYEXTENSIONSERVICE_H


#pragma once

#include "stdafx.h"
#include "SampleService.h"
#include "ReceiveSocketClient.h"
#include "SendSocketClient.h"
#include "CommunicatonProcessor.h"

class LiferayNativityExtensionService : public CServiceBase
{
public:

    LiferayNativityExtensionService(PWSTR pszServiceName, 
        BOOL fCanStop = TRUE, 
        BOOL fCanShutdown = TRUE, 
        BOOL fCanPauseContinue = FALSE);

	void Test(void);

    virtual ~LiferayNativityExtensionService(void);

protected:

    virtual void OnStart(DWORD dwArgc, PWSTR *pszArgv);
    
	virtual void OnStop();

    void ServiceWorkerThread(void);

private:
	CommunicatonProcessor* _communicationProcessor;
    BOOL stopped;
    HANDLE stoppedEvent;
	ReceiveSocketClient* _receiveSocketClient;
	SendSocketClient* _sendSocketClient;
};

#endif