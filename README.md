# muDep

The official repository of muDep.

## Usage

### Static binary analysis

* Extract shared object files from apps
  * Put the Android apps into the directory `apks/`
  * ``cd scripts``
  * ``python extractSo.py``
  * The _.so_ files will be extracted from apks to the path `scripts/so/`

* Specifying the path of IDA Pro on your system in `scripts/IDA_script/batch_run.py`, e.g.
  * `ida64_path = "C:\\Program Files\\IDA 7.0\\ida64.exe"`

* Launch the analysis
  * ``python batch_run.py``

Switching to, e.g. Windows may be needed due to the platform of IDA Pro you used. After the analysis, `scripts/txt_results/` holds the results of static binary analysis. Make sure to delete `scripts/so` and `scripts/txt_results` before you launch a new analysis.

### Mutation-based dependency generation and static taint analysis

`bin/prework.jar` is the program generator used to generate the code of Algorithm 2 in the paper. The code will be combined with `Fuzzing2.1` to derive a new Android app. The new app runs on the Android emulator to derive the dependency relations in _.json_ file. Since the _.so_ files in Android apps cannot be easily run outside an app, we find constructing the app a good way to perform dynamic analysis without modifying Android emulator. After deriving dependency relations, a modified version of DroidSafe generates the necessary stubs and performs the static taint analysis to figure out the sensitive flows.

* Install dependencies including `p7zip-full` and `p7zip-rar`.
* Create and deploy an Android emulator.
  * Assume the name of Android emulator is `muDep_emulator` and the image of sd-card is at `$HOME/Android/sdcard.img`. Configure `scripts/ActivateEmulator.sh` to adapt the emulator configurations on your own system.

* Prepare DroidSafe. Assume we have DroidSafe correctly deployed on your system,
  * Clean the original build of DroidSafe: `ant clean`
  * Substitute `src/` and `modeling/` directory of DroidSafe with the content in `droidsafe_modified/` of this repository.
  * Rebuild the new (modified) DroidSafe: `ant compile`
  * Another way to use the modified DroidSafe is to use the executable `bin/droidsafe-source.jar` in the `scripts/muDep.sh`

* Typical configurations of the dynamic analysis are deployed in `Fuzzing2.1/app/src/main/res/raw/configure.txt`, esp.
  * _mutateDepth_ : _depth_ of field, default=5
  * _testNum_ : _BOUND_ in Algorithm 2, default=15
  * Change these configurations as you need.

* Launch the Android emulator:
```bash
cd scripts
./ActivateEmulator.sh
```
* Launch the dynamic analysis and the static taint analysis (assume the target apks are in directory `apks/`):
```bash
cd scripts
python batch_dyn_analysis.py ../apks/
```
`batch_dyn_analysis.py` invokes `muDep.sh` to perform a two-phase analysis. The final reuslts of each app should be found at `$DROIDSAFE_SRC_HOME/android-apps/examples/[app_name]`

## Directory Structure

* bin
  * Related executables and tools.
* droidsafe_modified
  * Source code of the static taint analyzer DroidSafe (modified by muDep).
* example
  * The motivating example app and its source code. It also includes the analysis output of DroidSafe and JN-SAF, for comparison with our approach.
* Fuzzing2.1
  * Dynamic dependency generation of muDep.
* scripts
  * scripts of muDep.
* sources_sinks
  * The sources and sinks used by the IDA-based analysis of muDep.

## Contributors

* Yuwan Ma (18229047585 AT 163 DOT com)
* Cong Sun (suncong AT xidian DOT edu DOT cn)
