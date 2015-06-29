/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2014, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.jca.adapters.jdbc.jdk8;

import org.jboss.jca.adapters.jdbc.CachedPreparedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;

/**
 * CachedPreparedStatementJDK8.
 * 
 * @author <a href="jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public class CachedPreparedStatementJDK8 extends CachedPreparedStatement
{
   private static final long serialVersionUID = 1L;

   /**
    * Constructor
    * @param ps The prepared statement
    * @exception SQLException Thrown if an error occurs
    */
   public CachedPreparedStatementJDK8(PreparedStatement ps) throws SQLException
   {
      super(ps);
   }

   /**
    * {@inheritDoc}
    */
   public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException
   {
      getWrappedObject().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
   }

   /**
    * {@inheritDoc}
    */
   public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException
   {
      getWrappedObject().setObject(parameterIndex, x, targetSqlType);
   }

   /**
    * {@inheritDoc}
    */
   public long executeLargeUpdate() throws SQLException
   {
      return getWrappedObject().executeLargeUpdate();
   }
}
