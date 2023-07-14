import {forwardRef, Fragment, useImperativeHandle, useState} from "react";
import {BlockUI} from "primereact/blockui";
import {ProgressSpinner} from "primereact/progressspinner";
import React from "react";
const Loading = forwardRef(({}, ref) => {
    useImperativeHandle(ref, () => ({
        block() {
            setBlocked(true)
        },
        unBlock() {
            setBlocked(false)
        }
    }));

    const [blocked, setBlocked] = useState(false)

    return (
        <Fragment>
            <BlockUI blocked={blocked} fullScreen template={
                <ProgressSpinner animationDuration='1s'
                    pt={{
                    circle: { style: { stroke: 'green', strokeWidth: 3, animation: 'none'} }
                }}/>
            }
            />
        </Fragment>
        )
})

export default Loading;