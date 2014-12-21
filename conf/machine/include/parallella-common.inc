DEFAULTTUNE = "armv7ahf-neon"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-parallella"

require conf/machine/include/tune-zynq.inc
require conf/machine/include/machine-xilinx-default.inc

SERIAL_CONSOLE = "115200 ttyPS0"
