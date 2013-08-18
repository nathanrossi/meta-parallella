meta-parallella
===============

Yocto layer to support the Parallella board from Adapteva.

Supported Boards/Machines
=========================

Boards Supported by this layer (Please refer to the associate .conf for more
information):
 * Adapteva Parallella 16 (Generation 0)

Additional information on the Parallella at: 
 * http://www.parallella.org/

Additional information about Adapteva and the Epiphany architecture at:
 * http://www.adapteva.com/

Dependencies
============

This layer depends on:

	URI: git://git.openembedded.org/bitbake

	URI: git://git.openembedded.org/openembedded-core
	layers: meta

	URI: git://git.yoctoproject.org/meta-xilinx
	layers: meta-xilinx

Booting
=======

Example TFTP boot, place the following image from the build on your TFTP server.

 * core-image-minimal-parallella-16-gen0.ext2.gz.u-boot
 * uImage
 * uImage-parallella-16-gen0.dtb

Power Parallella board up without an SD card, at the U-Boot prompt execute the
following:

	U-Boot> set serverip <server ip address on your network>
	U-Boot> set ipaddr <valid static ip address on your network>
	U-Boot> tftp 0x2000000 core-image-minimal-parallella-16-gen0.ext2.gz.u-boot
	U-Boot> tftp 0x3000000 uImage
	U-Boot> tftp 0x2A00000 uImage-parallella-16-gen0.dtb
	U-Boot> bootm 0x3000000 0x2000000 0x2A00000

Please consult the meta-xilinx layer for additional booting information.
