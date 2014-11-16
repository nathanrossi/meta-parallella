meta-parallella
===============

Yocto layer to support the Parallella board from Adapteva.

Supported Boards/Machines
=========================

Boards Supported by this layer (Please refer to the associate .conf for more
information):
 * Adapteva Parallella (parallella)

Additional information on the Parallella http://www.parallella.org/.
For more information on the Epiphany architecture http://www.adapteva.com/.

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

Example SD rootfs boot, prepare your SD card by partitioning it with a small
(64 MB+) FAT partition and a larger ext4 partition.

Place the following files in the first partition. These files are found under
the deploy/images/parallella/ directory. Additionally select the correct
bitstream for your board (from deploy/images/parallella/bistreams).

 * uImage
 * parallella-mmc-boot.dtb (rename it as 'devicetree.dtb')
 * e.g. parallella_e16_headless_gpiose_7010.bit.bin (rename it as 'parallella.bit.bin')

Extract the rootfs image into the second ext4 partition. Assuming the rootfs
image built is named 'core-image-epiphany', extract the tar.gz from
deploy/images/core-image-epiphany-parallella.tar.gz into the root of the
partition (run the tar command as root/with sudo in order ot preserve file
system perms/options).

Power Parallella board up with the SD card. The system will boot up and load the
kernel, and then mount the rootfs from the second partition of the SD card.

