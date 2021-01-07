# muDep

The official repository of muDep.

## Operations

### Static binary analysis

```bash
cd scripts
python extractSo.py
```
The _.so_ files will be extracted from apks to the path _$muDepHome/so_. Then specifying the path of IDA Pro on your system in _$muDepHome/scripts/IDA_script/batch_run.py_, e.g.
```
ida64_path = "C:\\Program Files\\IDA 7.0\\ida64.exe"
```
After doing that,
```bash
python batch_run.py
```


## Directory Structure

* example
  * The example apk in the paper and its source code. It also includes the analysis output of other tools, i.e. DroidSafe and JN-SAF, for comparison with our approach.
* scripts
  * AAA
* sources_sinks
  * The sources and sinks used by the IDA-based analysis of muDep, and deployed to JN-SAF to make a fair comparison with muDep.
