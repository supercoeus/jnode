/*
JTestServer is a client/server framework for testing any JVM implementation.
 
Copyright (C) 2008  Fabien DUMINY (fduminy@jnode.org)

JTestServer is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

JTestServer is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package org.jtestserver.client.process.vmware;

import java.io.IOException;

import org.jtestserver.client.process.ServerProcess;

/**
 * Implementation of {@link ServerProcess} that starts a new 
 * <a href="http://www.vmware.com/">VMware</a> process.
 * 
 * @author Fabien DUMINY (fduminy@jnode.org)
 *
 */
public class VMwareServerProcess implements ServerProcess {
    /**
     * Configuration of machine to run in VMware.
     */
    private final VMwareConfig config;
    
    /**
     * Manager for the VMware machine.
     */
    private final VMware vmware;
    
    /**
     * 
     * @param config configuration of the machine to run in VMware server.
     */
    public VMwareServerProcess(VMwareConfig config) {
        this.config = config;
        vmware = new VMware(config);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start() throws IOException {
        vmware.start(config);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void stop() throws IOException {        
        vmware.stop(config);        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() throws IOException {
        boolean isRunning = false;
        for (String vm : vmware.getRunningVMs(config)) {
            if (config.getVmName().equals(vm)) {
                isRunning = true;
                break;
            }
        }
        
        return isRunning;
    }
}
