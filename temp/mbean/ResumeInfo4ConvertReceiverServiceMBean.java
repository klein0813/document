package com.augmentum.hrrs.mbean;

import java.util.Date;

import javax.management.Notification;
import javax.management.ObjectName;

import org.jboss.system.ServiceMBean;

public interface ResumeInfo4ConvertReceiverServiceMBean extends ServiceMBean {

    public void hit(
            Notification notification,
            Date date,
            long l,
            ObjectName objectname,
            String s);
}
