/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2015, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 1.0 as
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Eclipse
 * Public License for more details.
 *
 * You should have received a copy of the Eclipse Public License
 * along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.ironjacamar.core.workmanager.support;

import java.util.concurrent.CyclicBarrier;

import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkAdapter;
import javax.resource.spi.work.WorkManager;

/**
 * NestProviderWork allows to add contexts and nested works
 * 
 * @author <a href="mailto:vrastsel@redhat.com">Vladimir Rastseluev</a>
 *
 *
 */
public class NestProviderWork extends UniversalProviderWork
{
   private static final long serialVersionUID = 374498650817259221L;

   /**
    * current thread id
    */
   private String name;

   private WorkManager workManager = null;

   private Work nestWork = null;

   private boolean nestDo = false;

   private WorkAdapter wa;

   private CyclicBarrier barrier = null;
   
   /**
    * Constructor.
    * @param n this class name
    * @param a work adapter
    */
   public NestProviderWork(String n, WorkAdapter a)
   {
      name = n;
      wa = a;
   }

   /**
    * release method
    */
   public void release()
   {
      if (barrier != null)
      {
         try
         {
            barrier.await();
         }
         catch (Exception e)
         {
            throw new RuntimeException(e.getMessage());
         }
      }
   }

   /**
    * run method
    */
   public void run()
   {
      try
      {
         if (nestWork != null && workManager != null)
         {
            if (nestDo)
               workManager.doWork(nestWork, WorkManager.INDEFINITE, null, wa);
            else
               workManager.startWork(nestWork, WorkManager.INDEFINITE, null, wa);
         }
      }
      catch (Throwable e)
      {
         throw new RuntimeException(e.getMessage());
      }
   }

   /**
    * @param wm workManager
    */
   public void setWorkManager(WorkManager wm)
   {
      workManager = wm;
   }

   /**
    * @param work work
    */
   public void setWork(Work work)
   {
      nestWork = work;
   }

   /**
    * sets which method to execute nest work
    * @param exec - doWork if true startWork if false
    */
   public void setNestDo(boolean exec)
   {
      nestDo = exec;
   }

   /**
    * 
    * @return name of work
    */
   public String getName()
   {
      return name;
   }

   /**
    * setter
    * @param bar - new barrier value
    */
   public void setBarrier(CyclicBarrier bar)
   {
      barrier = bar;
   }

}
